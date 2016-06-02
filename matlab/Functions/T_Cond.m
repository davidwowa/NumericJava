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
        
        function t_cond = t_cond_manual(obj, norm_number)
            disp('start condition number manual')
            tic
            
            t_norm = T_Norm;
            t_norm.A = obj.A;
            
            toc
            disp('calcualte inverse')
            Ai = obj.A^-1;
            toc
            normA = t_norm.t_norm(norm_number);
            t_norm.A = Ai;
            normAi = t_norm.t_norm(norm_number);
            
            t_cond = normA*normAi;
            toc
        end
    end
end

