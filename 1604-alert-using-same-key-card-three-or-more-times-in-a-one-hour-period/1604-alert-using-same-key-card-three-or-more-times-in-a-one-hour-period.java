class Solution {
    public int changer(String str){
        String[] arr=str.split(":");
        return Integer.parseInt(arr[0])*60+Integer.parseInt(arr[1]);
    }
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        List<String> res=new ArrayList<>();
        Map<String,List<Integer>> map=new HashMap<>();
        for(int i=0;i<keyName.length;i++){
            map.putIfAbsent(keyName[i],new ArrayList<>());
            map.get(keyName[i]).add(changer(keyTime[i]));
        }
        for(String s:map.keySet()){
            List<Integer> list=map.get(s);
            Collections.sort(list);
            for(int i=0;i+2<list.size();i++){
                if(list.get(i+2)-list.get(i)<=60){
                    res.add(s);
                    break;
                }
            }
        }
        Collections.sort(res);
        return res;
    }
}