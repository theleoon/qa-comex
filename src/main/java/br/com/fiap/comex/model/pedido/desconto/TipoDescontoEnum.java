package br.com.fiap.comex.model.pedido.desconto;

import java.math.BigDecimal;

public enum TipoDescontoEnum {
    QUANTIDADE{
        public BigDecimal percentual(){
            return new BigDecimal(0.1);
        }
    },
    PROMOCAO{
        public BigDecimal percentual(){
            return new BigDecimal("0.1");
        }
    },
    NENHUM{
        public BigDecimal percentual(){
            return BigDecimal.ZERO;
        }
    },
    FIDELIDADE{
        public BigDecimal percentual(){
            return new BigDecimal(0.05);
        }
    };

    public abstract BigDecimal percentual();
}
