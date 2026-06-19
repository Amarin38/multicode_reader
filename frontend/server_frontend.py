import http.server
import ssl
import os
import socket

def main():
    directorio_actual = os.path.dirname(os.path.abspath(__file__))

    cert_path = os.path.join(directorio_actual, "cert.pem")
    key_path = os.path.join(directorio_actual, "key.pem")

    puerto = 443
    server_address = ('0.0.0.0', puerto)

    httpd = http.server.HTTPServer(server_address, http.server.SimpleHTTPRequestHandler)

    contexto = ssl.SSLContext(ssl.PROTOCOL_TLS_SERVER)
    contexto.load_cert_chain(certfile=cert_path, keyfile=key_path)

    httpd.socket = contexto.wrap_socket(httpd.socket, server_side=True)

    print(f"Servidor HTTPS corriendo. Acceso: https://{obtener_ip_local()}:{puerto}")
    httpd.serve_forever()

def obtener_ip_local():
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    try:
        s.connect(('8.8.8.8', 80))
        ip = s.getsockname()[0]
    except Exception:
        ip = '127.0.0.1'
    finally:
        s.close()
    return ip

if __name__ == '__main__':
    main()