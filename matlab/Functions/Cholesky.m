classdef Cholesky
    properties
        A
    end
    
    methods
        function result = cholesky(obj, A)
            tic
            result = chol(obj.A);
            toc
        end
    end
end    
