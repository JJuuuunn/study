package smOrders.domain;

public class ShoppingMall {
    // 쇼핑몰 id
    private Long id;
    // 쇼핑몰 명
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ShoppingMall{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
