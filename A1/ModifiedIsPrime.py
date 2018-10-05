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
    
isPrimem(2)

