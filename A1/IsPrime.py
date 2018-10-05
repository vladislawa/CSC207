def isPrime():
    '''
    isPrime(n) which returns whether integer n is prime.
    
    '''
    n = input("Enter your integer   ")
    
    if type(n) == int:
        if n == 2:
            print("is a prime number")
        
        elif n > 1:
            for i in range(2,n):
                if n%i == 0:
                    
                    print("is not a prime number")
                    break
                else:
                    
                    print("is a prime number")
                    break
        else:
            print("is not a prime number")        
        
    else:
        print("invalid input")
        isPrime()
    
isPrime()