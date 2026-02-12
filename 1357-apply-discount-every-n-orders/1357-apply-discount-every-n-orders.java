class Cashier {
    int discount;
    int n;
    int []products;
    int []prices=new int[200];
    public Cashier(int n, int discount, int[] products, int[] prices) {
        this.discount=discount;
        this.n=n;
        this.products=products;
        for(int i=0; i<products.length; i++){
            this.prices[products[i]-1]=prices[i];
        }
    }
    int count=1;
    public double getBill(int[] product, int[] amount) {
        double cost=0;
        for(int i=0; i<product.length; i++){
            cost+=amount[i]*this.prices[product[i]-1];    
        }
        if(count==n){
            cost*=(double)(100-this.discount)/100.00;
            count=1;         
        }else{
            count++;
        }        
        return cost;
    }
}