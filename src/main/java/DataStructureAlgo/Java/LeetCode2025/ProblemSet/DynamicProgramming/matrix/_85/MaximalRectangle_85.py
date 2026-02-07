from typing import List

class Solution:

    def larget_rect_histogram2(self, heights) -> int : 
        stack = [-1]
        max_area = 0 
        for i,height in enumerate(heights) : 
            while heights[stack[-1]] > height : 
                h = heights[stack.pop()]
                w = i - stack[-1] - 1
                max_area = max(max_area, h*w)
            stack.append(i)
        
        return max_area

    def larget_rect_histogram(self, heights) -> int : 
        stack = [-1]
        max_area = 0 
        for i,height in enumerate(heights) : 
            while stack[-1] !=-1 and heights[stack[-1]] >= height : 
                h = heights[stack.pop()]
                w = i - stack[-1] - 1
                max_area = max(max_area, h*w)
            stack.append(i)
        
        return max_area



    def maximalRectangle(self, matrix: List[List[str]]) -> int:
        row, col = len(matrix), len(matrix[0])

        heights = [0]*(col+1) # dummy 0 height at the end
        larget_rect_area = 0
        for row in matrix:
            for i in range(col) : 
                heights[i] = heights[i] + 1 if row[i] == "1" else 0
            
            larget_rect_area = max(larget_rect_area, self.larget_rect_histogram2(heights))
        
        return larget_rect_area

                