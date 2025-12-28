class ThroneInheritance {

    Map<String, List<String>> children;
    Set<String> dead;
    String king;

    public ThroneInheritance(String kingName) {
        children = new HashMap<>();
        dead = new HashSet<>();
        children.put(kingName, new ArrayList<>());
        king = kingName;
    }
    
    public void birth(String parentName, String childName) {
        children.putIfAbsent(parentName, new ArrayList<>());
        children.get(parentName).add(childName);
    }
    
    public void death(String name) {
        dead.add(name);
    }
    
    public List<String> getInheritanceOrder() {
        List<String> order = new ArrayList<>();
        dfs(king, order);
        return order;
    }

    public void dfs(String name, List<String> thrones) {
        if (!dead.contains(name)) {
            thrones.add(name);
        }
        List<String> childs = children.get(name);
        if (childs == null) return;
        for (String a : childs) {
            dfs(a, thrones);
        }
    }
}