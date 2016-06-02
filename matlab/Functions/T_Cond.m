classdef T_Cond
    
    properties
        A
    end
    
    methods
        function t_cond = t_cond(obj)
            disp('start condition number')
            tic
            t_cond = cond(obj.A);
            toc
        end
    end
end

