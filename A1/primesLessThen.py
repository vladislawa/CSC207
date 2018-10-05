def isPrimem(n):
    '''
    isPrimem(n) which returns whether integer n is prime.
    
    '''
    
    if n == 2:
        return True
    
    if n > 1:
        for i in range(2,n):
            if n%i==0:
                return False
        else:
            return True
    else:
        return False
    


def primesLessThan(n):
    '''
    primesLessThan(n) which returns an ArrayList of primes less than n by repeatedly calling isPrime on 2,3,4,...,n-1. 
    '''
    lst = []
    for i in range(2,n-1):
        if isPrimem(i) == True:
            lst.append(i)
    print(lst)
    
primesLessThan(40)