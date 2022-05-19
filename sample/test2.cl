import Math
import Object
import Random
import Person
import String

class Employee(Person){
    string first_name
    string last_name
    int age
    Person[10] friends
    float weight
    bool is_married

    def Employee(string first_name){
        self.first_name = first_name
    }

    def Employee(string first_name, string last_name){
        self.first_name = first_name
        self.last_name = last_name
    }

    def Employee(string first_name, string last_name, float weight, bool is_married){
        self.first_name = first_name
        self.last_name = last_name
        self.age = Random(18,80)
        self.weight = weight
        self.is_married = is_married
    }
    
    def Employee(string first_name, string last_name, int age, float weight, bool is_married){
        self.first_name = first_name
        self.last_name = last_name
        self.age = self.set_age(age)
        self.weight = weight
        self.is_married = is_married
    }

    def void set_age(int age){
        # age must be greater than 0
        if(age < 0 or age == 0){
            age = 0 - age
        }
        elif(age >= 100){
            print(age)
        }
        else{
            age = age
        }

        return age
    }

    def void increment_age(){
        self.age += 1
    }

    def string get_full_name(){
        return concat(self.first_name, self.last_name)
    }

    def void exercise(float ideal_weight){
        #*
            Exercise to lose weight:
            when this method is called,
            the person will begin to exercise
            and lose weight until they reach
            the ideal weight
        *#
        while(self.weight > ideal_weight){
            self.weight -= 0.1
        }
    }

    def void main(){
        Person[20] friends = Person()[20]

        int behzad_age = 21

        employee_emad = Employee(emad)
        employee_Behzad = Employee(behzad, shomali)

        int max_age = max(employee_emad.age, employee_Behzad.age)
        print(max_age)

        for i in range(1, 20, 5){ # 1, 6, 11, 16
            print(friends[2].first_name)
        }
    }
}