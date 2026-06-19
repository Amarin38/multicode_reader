import http.server
import ssl
import os
from pathlib import Path
import socket

class Handler(http.server.SimpleHTTPRequestHandler):
    def __init__(self, *args, directory=None, **kwargs):
        super().__init__(*args, directory=directory, **kwargs)

def main():
    directorio_padre = str(Path(__file__).resolve().parent.parent)

    cert_path = os.path.join(os.path.dirname(os.path.abspath(__file__)), "credentials/cert.pem")
    key_path = os.path.join(os.path.dirname(os.path.abspath(__file__)), "credentials/key.pem")

    puerto = 443
    server_address = ('0.0.0.0', puerto)

    def handler_factory(*args, **kwargs):
        return Handler(*args, directory=directorio_padre, **kwargs)

    httpd = http.server.HTTPServer(server_address, handler_factory)

    contexto = ssl.SSLContext(ssl.PROTOCOL_TLS_SERVER)
    contexto.load_cert_chain(certfile=cert_path, keyfile=key_path)

    httpd.socket = contexto.wrap_socket(httpd.socket, server_side=True)

    print(f"Servidor HTTPS servirá: {directorio_padre}")
    print(f"Acceso: https://{obtener_ip_local()}:{puerto}")
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