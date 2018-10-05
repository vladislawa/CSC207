def primes_less_then_sieve_remove(n):
    lst = []
    q = 0
    for i in range (2, n):
        lst.append(i)
    
    while q < len(lst):
        k = q + 1
        while k < len(lst):
            if lst[k] % lst[q] == 0:
                del lst[k]
            k = k + 1
        q = q + 1
        
    print(lst)
primes_less_then_sieve_remove(40)

            