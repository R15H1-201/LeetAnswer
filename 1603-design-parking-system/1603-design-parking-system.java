class ParkingSystem {
    private int[] availableSpaces;

    public ParkingSystem(int big, int medium, int small) {
        availableSpaces = new int[] {0, big, medium, small};
    }
    
    public boolean addCar(int carType) {
        if(availableSpaces[carType] == 0){
            return false;
        }
        availableSpaces[carType]--;
        return true;
    }
}
/**
 * Your ParkingSystem object will be instantiated and called as such:
 * ParkingSystem obj = new ParkingSystem(big, medium, small);
 * boolean param_1 = obj.addCar(carType);
 */