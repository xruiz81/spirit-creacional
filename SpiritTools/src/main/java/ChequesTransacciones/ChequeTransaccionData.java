package ChequesTransacciones;

public class ChequeTransaccionData {

	Long id;
	Long chequeEmitidoId;
	Long transaccionId;
	String origen;
	
	public ChequeTransaccionData() {
	}
	
	public ChequeTransaccionData(Long id,
	Long chequeEmitidoId,
	Long transaccionId,
	String origen) {
		this.id = id;
		this.chequeEmitidoId = chequeEmitidoId;
		this.transaccionId = transaccionId;
		this.origen = origen;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getChequeEmitidoId() {
		return chequeEmitidoId;
	}

	public void setChequeEmitidoId(Long chequeEmitidoId) {
		this.chequeEmitidoId = chequeEmitidoId;
	}

	public Long getTransaccionId() {
		return transaccionId;
	}

	public void setTransaccionId(Long transaccionId) {
		this.transaccionId = transaccionId;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}
	
}
