const EmpleadoEnum = Object.freeze({
    PRUEBA1: "Prueba1",
    PRUEBA2: "Prueba2",
    PRUEBA3: "Prueba3"
});

const PantallaEnum = Object.freeze({
   CONFIGURACION: "Configuracion",
   ESCANER: "Escaner"
});

document.addEventListener('alpine:init', () => {
    const ipActual = window.location.hostname;

    Alpine.data('escanerCamara', () => ({
        // --- CONTROL DE PANTALLA ---
        pantallaActual: PantallaEnum.CONFIGURACION,

        // --- DATOS DEL PEDIDO ---
        razonSocial: '',
        fecha: new Date().toLocaleDateString('es-ES'),
        empleadoAsignado: '',

        // --- DATOS DEL SCANER ---
        codigoLeido: '',
        cantidad: 1,
        listaCodigos: [],
        html5QrCode: null,
        camaraEncendida: false,

        iniciarEscaneo() {
            if (this.razonSocial && this.empleadoAsignado) {
                this.pantallaActual = PantallaEnum.ESCANER;
                this.escanear();
            } else {
                alert("Por favor, completa los campos.");
            }
        },

        volver() {
            this.pantallaActual = PantallaEnum.CONFIGURACION;
            this.detenerEscaner();
        },

        escanear() {
            const self = this;
            self.html5QrCode = new Html5Qrcode("lector-camara");

            self.html5QrCode.start(
                { facingMode: "environment" },
                {
                    fps: 30,
                    qrbox: { width: 350, height: 150 },
                    formatsToSupport: [Html5QrcodeSupportedFormats.CODE_128, Html5QrcodeSupportedFormats.EAN_13]
                },
                (codigo) => {
                    const formato = /^\d{3}\.\d{5}$/;
                    if (codigo && codigo.trim() !== '' && formato.test(codigo)) {
                        self.codigoLeido = codigo;
                        self.detenerEscaner();
                    }
                },
                (error) => {}
            ).then(() => { self.camaraEncendida = true; })
                .catch((err) => { console.error(err); });
        },

        detenerEscaner() {
            if (this.html5QrCode && this.camaraEncendida) {
                this.html5QrCode.stop().then(() => { this.camaraEncendida = false; });
            }
        },

        guardarItem() {
            if (this.codigoLeido !== '') {
                this.listaCodigos.push({
                    codigo: this.codigoLeido,
                    cantidad: parseInt(this.cantidad, 10) || 0
                });
                this.codigoLeido = '';
                this.cantidad = 1;
                this.escanear();
            }
        },

        cancelarLectura() {
            this.codigoLeido = '';
            this.cantidad = 0;
            this.escanear();
        },

        eliminarCodigo(index) {
            this.listaCodigos.splice(index, 1);
        },

        async enviarDatos() {
            console.log("Intentando enviar:", this.listaCodigos);
            if (this.listaCodigos.length === 0) return alert("No hay códigos.");

            const payload = {
                razonSocial: this.razonSocial,
                fecha: this.fecha,
                empleadosAsignados: [this.empleadoAsignado],
                codigos: this.listaCodigos
            };

            try {
                const respuesta = await fetch(`http://${ipActual}:8080/api/pedido/guardar`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(payload)
                });
                if (respuesta.ok) {
                    alert("Datos guardados.");
                    this.listaCodigos = [];
                    this.volver();
                }
            } catch (error) { alert("Error de conexión"); }
        }
    }))
})