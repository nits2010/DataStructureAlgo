from typing import List
class Solution:
    def maximalSquare(self, matrix: List[List[str]]) -> int:
        row, col = len(matrix), len(matrix[0])
        max_area = 0

        square = [[0]*col for _ in matrix ]

        for j in range(col):
            square[0][j] = int(matrix[0][j])
            max_area = max(max_area, square[0][j])
        
        for i in range(row):
            square[i][0] = int(matrix[i][0])
            max_area = max(max_area, square[i][0])


        for i in range(1,row):
            for j in range(1,col):
                if int(matrix[i][j]) : 
                    square[i][j] = min(square[i-1][j], square[i][j-1], square[i-1][j-1]) + 1
                
                max_area = max(max_area, square[i][j])
        return max_area*max_area


class Solution_InputAsInt:
    def maximalSquare(self, matrix: List[List[str]]) -> int:
        row, col = len(matrix), len(matrix[0])
        max_area = 0

        square = [[0]*col for _ in matrix ]
        for i in range(row):
            for j in range(col):
                square[i][j] = int(matrix[i][j])

                if i > 0 and j > 0 and square[i][j] : 
                    square[i][j] = min(square[i-1][j], square[i][j-1], square[i-1][j-1]) + 1
                
                max_area = max(max_area, square[i][j])
        return max_area*max_area