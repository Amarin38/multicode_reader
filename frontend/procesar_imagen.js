// Archivo: lector.js

document.addEventListener('alpine:init', () => {
    Alpine.data('escanerCamara', () => ({
        codigoLeido: '',
        escaner: null,

        iniciarEscaner() {
            this.escaner = new Html5QrcodeScanner(
                "lector-camara",
                { fps: 10, qrbox: { width: 250, height: 250 } },
                false
            );

            this.escaner.render(
                (codigo) => {
                    this.codigoLeido = codigo;
                    this.escaner.clear();
                },
                (error) => {} // Ignoramos errores de enfoque
            );
        },

        reiniciarLector() {
            this.codigoLeido = '';
            this.iniciarEscaner();
        }

    }))
})