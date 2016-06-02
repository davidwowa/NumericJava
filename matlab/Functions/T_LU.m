classdef T_LU
    
    properties
        A
    end
    
    methods
        function [L, U] = t_lu(obj)
            disp('start lu decomposition')
            tic
            [L, U] = lu(obj.A);
            toc
        end
        function [L, U, P] = t_lup(obj)
            disp('start lu decomposition')
            tic
            [L, U, P] = lu(obj.A);
            toc
        end
    end
end

