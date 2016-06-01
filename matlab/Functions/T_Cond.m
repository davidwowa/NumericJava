classdef T_Cond
    
    properties
        A
    end
    
    methods
        function t_cond = t_cond(obj, A)
            tic
            t_cond = cond(A);
            toc
        end
    end
end

