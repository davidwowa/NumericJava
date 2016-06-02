classdef T_Cholesky
    properties
        A
    end
    
    methods
        function [L, R] = cholesky(obj)
            disp('start cholesky decomposition')
            mc = MatrixChecks;
            mc.A = obj.A;
            isPositiveDefinite = mc.isPositiveDefinit;
            if isPositiveDefinite == 0
                tic
                L = chol(obj.A, 'lower');
                R = chol(obj.A, 'upper');
                toc
            end
        end
    end
end
