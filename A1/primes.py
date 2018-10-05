def primes(n):
    '''
    primes(n) which returns an array of the first n primes.
    
    '''
    lst = []
    if type(n) == int:
        
        for e in range(1,n):
            
            if e == 2:
                lst.append(e)
                
            if e > 2:
                for i in range(2,e):
                    if e%i == 0:
                        break      
                        """WHATS THE DIFFERENCE IS LEVELS? WHAT IF I PUT ELSE UNDER IF"""
                else:
                    lst.append(e)
                        
                        
                
    else:
        print("invalid")
        primes(input("input the INTEGER"))
        
    print(lst)
primes(40)
            
        

            
