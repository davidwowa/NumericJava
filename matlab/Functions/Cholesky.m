classdef Cholesky
    properties
        A
    end
    
    methods
        function result = cholesky(obj, A)
            disp('start cholesky decomposition')
            tic
            result = chol(obj.A);
            toc
        end
    end
end    
