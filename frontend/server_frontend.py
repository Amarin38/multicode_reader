import http.server
import ssl

def main():
    puerto = 4443
    server_address = ('0.0.0.0', puerto)

    httpd = http.server.HTTPServer(server_address, http.server.SimpleHTTPRequestHandler)

    contexto = ssl.SSLContext(ssl.PROTOCOL_TLS_SERVER)
    contexto.load_cert_chain(certfile="cert.pem", keyfile="key.pem")
    httpd.socket = contexto.wrap_socket(httpd.socket, server_side=True)

    print(f"Servidor HTTPS corriendo. Acceso: https://192.168.0.192:{puerto}")
    httpd.serve_forever()

if __name__ == '__main__':
    main()