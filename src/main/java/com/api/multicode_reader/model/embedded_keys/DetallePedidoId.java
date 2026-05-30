package com.api.multicode_reader.model.embedded_keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DetallePedidoId implements Serializable {
    @Column(name = "pedido_id")
    private Long pedidoId;

    @Column(name = "codigo_id")
    private Long codigoId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DetallePedidoId that = (DetallePedidoId) o;
        return Objects.equals(pedidoId, that.pedidoId) &&
               Objects.equals(codigoId, that.codigoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedidoId, codigoId);
    }
}
