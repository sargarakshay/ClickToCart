package model;

public class Cart {
    private int cartId;
    private Product product;
    private Customer customer;
    private int cartProductQuantity;
    private double cartProductTotal;

    public Cart() {
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getCartProductQuantity() {
        return cartProductQuantity;
    }

    public void setCartProductQuantity(int cartProductQuantity) {
        this.cartProductQuantity = cartProductQuantity;
    }

    public double getCartProductTotal() {
        return cartProductTotal;
    }

    public void setCartProductTotal(double cartProductTotal) {
        this.cartProductTotal = cartProductTotal;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", product=" + product +
                ", customer=" + customer +
                ", cartProductQuantity=" + cartProductQuantity +
                ", cartProductTotal=" + cartProductTotal +
                '}';
    }
}
