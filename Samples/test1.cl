import Nothing
import Nothing2

class Human(Nothing, Nothing2){
    Nose nose
    Hand[2] hands
    Leg[2] legs
    int calories
    bool isHungry

    def Human(Nose n){
        self.nose = n
    }
    def Voice speak(){
        Voic voice
        return voice
    }
    def void eat(Food food, int c){
        calories += c
        newFood = food
        while(self.isHungry){
            Food newFood = Food()
            eat(newFood)
            self.isHungry = self.checkIsHungry()
        }
    }
    def bool checkIsHungry(){
        return true
    }
}
class Human(Nothing, Nothing2){
    Nose nose
    Hand[2] hands
    Leg[2] legs
    int calories
    bool isHungry

    def Human(Nose n){
        self.nose = n
    }
    def Voice speak(){
        Voic voice
        return voice
    }
    def void eat(Food food, int c){
        calories += c
        newFood = food
        while(self.isHungry){
            Food newFood = Food()
            eat(newFood)
            self.isHungry = self.checkIsHungry()
            if (true) {
               Kia kiki
            }
        }
    }
    def void main(){

    }
}