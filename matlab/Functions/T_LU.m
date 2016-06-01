classdef T_LU
    
    properties
        A
    end
    
    methods
        function [L, U] = t_lu(obj, A)
            tic
            [L, U] = lu(obj.A);
            toc
        end
    end
end

