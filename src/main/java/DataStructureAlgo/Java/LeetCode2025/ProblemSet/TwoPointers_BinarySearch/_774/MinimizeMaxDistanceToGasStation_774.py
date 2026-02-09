class Solution:
    def minMaxDist(self, stations, k):
        def is_possible(d):
            total = 0 
            for i in range(1, len(stations)):
                total +=  (stations[i] - stations[i-1]) // d
                if total > k:
                    return False
            
            return total <= k
        
               
        low = 0
        high = stations[-1] - stations[0]
        
        distance = high
        eps = 1e-6

        
        while (high - low ) > eps :
            
            mid = (low + high) / 2.0
            # print(f"low={low}, high={high}, mid={mid}")
            
            # assume mid is the lowest possible distance, with this we need to 
            # see what is the distance comes out wrt to all stations
            if is_possible(mid):
                distance = mid
                high = mid 
            else:
                low = mid
        
        return distance
        