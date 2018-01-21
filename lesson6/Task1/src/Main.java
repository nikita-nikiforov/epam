import java.util.Collection;

interface UnaryPredicate<T>{
    boolean test(T obj);
}

class OddPredicate implements UnaryPredicate<Integer>{
    public boolean test(Integer i){
        return i%2 != 0;
    }
}

class PrimePredicate implements UnaryPredicate<Integer>{
    public boolean test(Integer number){
        if(number%2==0){
            return false;
        }
        for(int i=3; i*i<=number;i+=2){
            if(number%i==0){
                return false;
            }
        }
        return true;
    }
}

final class Algorithm{
    public static <T> int countIf(Collection<T> collection, UnaryPredicate<T> predicate){
        int result = 0;
        for (T object : collection){
            if(predicate.test(object)) result++;
        }
        return result;
    }
}
