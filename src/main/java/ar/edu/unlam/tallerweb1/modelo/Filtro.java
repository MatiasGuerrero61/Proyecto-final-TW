package ar.edu.unlam.tallerweb1.modelo;

public class Filtro {

    private String descripcion;
    private Integer precioMax;
    private Integer precioMin;
    private Integer idCategoria;
    private String order;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPrecioMax() {
        return precioMax;
    }

    public void setPrecioMax(Integer precioMax) {
        if (precioMax != null) {
            this.precioMax = precioMax;
        } else {
            this.precioMax = 9999;
        }
    }

    public Integer getPrecioMin() {
        return precioMin;
    }

    public void setPrecioMin(Integer precioMin) {
        if (precioMin != null) {
            this.precioMin = precioMin;
        } else {
            this.precioMin = 0;
        }

    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }


}
