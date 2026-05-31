CREATE TABLE codigo
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    familia     VARCHAR(255)          NOT NULL,
    articulo    VARCHAR(255)          NOT NULL,
    decrcipcion VARCHAR(255)          NULL,
    CONSTRAINT pk_codigo PRIMARY KEY (id)
);

CREATE TABLE detalle_pedido
(
    cantidad  DOUBLE NOT NULL,
    pedido_id BIGINT NOT NULL,
    codigo_id BIGINT NOT NULL,
    CONSTRAINT pk_detallepedido PRIMARY KEY (pedido_id, codigo_id)
);

CREATE TABLE empleado
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    nombre   VARCHAR(255)          NOT NULL,
    apellido VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_empleado PRIMARY KEY (id)
);

CREATE TABLE empleado_asignado
(
    pedido_id   BIGINT NOT NULL,
    empleado_id BIGINT NOT NULL,
    CONSTRAINT pk_empleadoasignado PRIMARY KEY (pedido_id, empleado_id)
);

CREATE TABLE pedido
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    razon_social VARCHAR(255)          NOT NULL,
    fecha_pedido date                  NOT NULL,
    tipo_pedido  VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_pedido PRIMARY KEY (id)
);

ALTER TABLE detalle_pedido
    ADD CONSTRAINT FK_DETALLEPEDIDO_ON_CODIGO FOREIGN KEY (codigo_id) REFERENCES codigo (id);

ALTER TABLE detalle_pedido
    ADD CONSTRAINT FK_DETALLEPEDIDO_ON_PEDIDO FOREIGN KEY (pedido_id) REFERENCES pedido (id);

ALTER TABLE empleado_asignado
    ADD CONSTRAINT FK_EMPLEADOASIGNADO_ON_EMPLEADO FOREIGN KEY (empleado_id) REFERENCES empleado (id);

ALTER TABLE empleado_asignado
    ADD CONSTRAINT FK_EMPLEADOASIGNADO_ON_PEDIDO FOREIGN KEY (pedido_id) REFERENCES pedido (id);