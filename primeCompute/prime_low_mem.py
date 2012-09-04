class CloneableLazyList(object):
    def __init__(self, gen):
        self.gen = gen
        self.cache = []
        self.index = 0
    def __iter__(self):
        return self
    def next(self):
        if self.index < len(self.cache):
            ret = self.cache[self.index]
            self.index += 1
            return ret
        else:
            ret = self.gen.next()
            self.index += 1
            self.cache.append(ret)
            return ret
    def clone(self): # shared cache and gen between copies
        ret = CloneableLazyList(self.gen)
        ret.cache = self.cache
        ret.index = self.index
        return ret

def count_prime(N):
    if N < 2:return 0
    elif N < 3:return 1
    elif N < 5:return 2
    elif N < 7:return 3
    elif N < 11:return 4
    elif N < 12:return 5
    else:
        lazy_init_list = CloneableLazyList((x for x in xrange(11, N, 1) if x % 2 != 0 and x % 3 != 0 and x % 5 != 0 and x % 7 != 0))
        init_list_length = N - 1 - N / 2 - N / 3 - N / 5 - N / 7 + N / (2 * 3) + N / (3 * 7) + N / (5 * 7) + N / (2 * 5) + N / (2 * 7) + N / (3 * 5) - N / (2 * 3 * 5) - N / (3 * 5 * 7) - N / (2 * 5 * 7) - N / (2 * 3 * 7) + N / (2 * 3 * 5 * 7)
        
        removed = set()
        prime_gen = (x for x in lazy_init_list if x not in removed)# TODO remove from 'removed' once hit
        
        p = prime_gen.next()
        sqrt_n = int(N ** 0.5)
        
        while p <= sqrt_n:
            mult_seq = lazy_init_list.clone()
            mult = p
            m = p * mult
            while m <= N:
                removed.add(m)
                mult = mult_seq.next()
                m = p * mult
            p = prime_gen.next()
        
        return init_list_length - len(removed) + 4

import time
start = time.time()
print count_prime(1000000)
print 'time elapsed: %ss' % `time.time() - start`
