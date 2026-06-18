// Archivo: lector.js

document.addEventListener('alpine:init', () => {
    Alpine.data('escanerCamara', () => ({

        codigoLeido: '',
        cantidad: 1,
        listaCodigos: [],
        html5QrCode: null,
        camaraEncendida: false,

        iniciarEscaner() {
            const self = this;
            const ipActual = window.location.hostname;

            self.html5QrCode = new Html5Qrcode("lector-camara");

            self.html5QrCode.start(
                { facingMode: "environment" },
                {
                    fps: 30,
                    qrbox: { width: 350, height: 150 },
                    formatsToSupport: [
                        Html5QrcodeSupportedFormats.CODE_128,
                        Html5QrcodeSupportedFormats.EAN_13
                    ]
                },
                (codigo) => {
                    const formatoDeseado = /^\d{3}\.\d{5}$/;

                    if (codigo && codigo.trim() !== '' && formatoDeseado.test(codigo)) {

                        self.codigoLeido = codigo;
                        self.detenerEscaner();

                    } else {
                        console.log("Se leyó un código, pero no tiene el formato 000.00000:", codigo);
                    }
                },
                (error) => {}
            ).then(() => {
                self.camaraEncendida = true;
            }).catch((err) => {
                console.error("No se pudo iniciar la cámara:", err);
            });
        },

        detenerEscaner() {
            const self = this;
            if (self.html5QrCode && self.camaraEncendida) {
                self.html5QrCode.stop().then(() => {
                    self.camaraEncendida = false;
                }).catch((err) => console.error(err));
            }
        },

        guardarItem() {
            if (this.codigoLeido !== '') {
                let cantidadEntera = parseInt(this.cantidad, 10) || 0;

                this.listaCodigos.push({
                    codigo: this.codigoLeido,
                    cantidad: cantidadEntera
                });

                this.codigoLeido = '';
                this.cantidad = '';
                this.iniciarEscaner();
            }
        },

        cancelarLectura() {
            this.codigoLeido = '';
            this.datoExtra = '';
            this.iniciarEscaner();
        },

        eliminarCodigo(index) {
            this.listaCodigos.splice(index, 1);
        },

        async enviarDatos() {
            if (this.listaCodigos.length === 0) {
                alert("No hay códigos para enviar.")
                return;
            }

            try {
                const urlBackend = `http://${ipActual}:8080/api/pedido/guardar`;

                const respuesta = await fetch(urlBackend, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(this.listaCodigos)
                });
                if (respuesta.ok) {
                    alert("Datos guardados correctamente.");
                    this.listaCodigos = [];
                } else {
                    alert("error al guardar: " + respuesta.status);
                }
            } catch (error){
                console.error("Error de conexión:", error);
                alert("No se pudo conectar con el servidor.")
            }
        }
    }))
})