package framework.annotations;

class AmbigiousConstructorClass {
    private String name;
    private String phoneNumber;

    @Inject
    public AmbigiousConstructorClass(String name) {
        this.name = name;
    }

    @Inject
    public AmbigiousConstructorClass(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
