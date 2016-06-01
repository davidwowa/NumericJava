classdef T_Norm
    
    properties
        A
    end
    
    methods
        %The only matrix norms available are 1, 2, inf, and 'fro'.
        function t_norm = t_norm(obj, A, normNumber)
            tic
            t_norm = norm(obj.A, normNumber);
            toc
        end
    end
end
