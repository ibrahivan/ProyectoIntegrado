package FarmaSupply.daos;

public enum EstadoPedido {
	PENDIENTE("Pendiente"), 
	CAMINO("En camino"),
	ENTREGADO("Entregado");
	
	 private final String displayName;

    EstadoPedido(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}
