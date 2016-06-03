classdef MatrixChecks
    
    properties
        A
        b
    end
    
    methods
        function x=backSubstitution(obj,b,n)
            % Solving an upper triangular system by back-substitution
            % Input matrix U is an n by n upper triangular matrix
            % Input vector b is n by 1
            % Input scalar n specifies the dimensions of the arrays
            % Output vector x is the solution to the linear system
            % U x = b
            % K. Ming Leung, 01/26/03
            
            x=zeros(n,1);
            for j=n:-1:1
                if (obj.A(j,j)==0); error('Matrix is singular!'); end;
                x(j)=b(j)/obj.A(j,j);
                b(1:j-1)=b(1:j-1)-obj.A(1:j-1,j)*x(j);
            end
        end
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

