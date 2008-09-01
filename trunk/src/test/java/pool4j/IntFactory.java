package pool4j;

class IntFactory implements ResourceFactory<Object> {
    public Object createResource() {
        return new Integer(5);
    }
}
