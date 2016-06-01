classdef LeastSquare
    
    properties
    A
    b
    end
    
    methods
        function Ar = rebuildMatrixForLeasSquares(obj, polynom_grad)
           numberOfRows = size(obj.A, 1);
           Ar = zeros(numberOfRows, polynom_grad); 
           for i=1:1:polynom_grad            
            for j = 1:1:numberOfRows
                if i == 1
                    Ar(j, i) = 1;
                else
                    value = obj.A(j, 1);
                    
                    value = value^(i-1);
                    
                    Ar(j, i) = value;
                end
            end
           end
        end
        function result = solveLeastSquare(obj, polynom_grad)
            tic
            Ar = rebuildMatrixForLeasSquares(obj, polynom_grad);
            At = transpose(Ar);
            At_times_A = At * Ar;
            new_B = At * obj.b;
            toc
            % DANGER !!!
            result = At_times_A\new_B;
            toc
        end
    end
end

