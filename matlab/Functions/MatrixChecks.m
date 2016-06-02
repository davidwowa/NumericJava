classdef MatrixChecks
    
    properties
        A
        b
    end
    
    methods
        function result = isPositiveDefinit(obj)
            disp('start calcualte eigenvalues')
            tic
            eig_A = eig(obj.A);
            toc
            result = 0;
            for i = 1:rank(obj.A)
                if eig_A(i) <= 0
                    result = 1;
                end
            end
            if result == 1
                disp('the matrix is not positive definite')
            else
                disp('the matrix is positive definite')
            end
        end
        
        function result = isSingular(obj)
            disp('is matrix singular over det')
            result = det(obj.A);
            
            if result ~= 0
                disp('matrix is regular')
            else
                disp('matris is singular')
            end
        end
    end
end

