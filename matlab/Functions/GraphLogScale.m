classdef GraphLogScale
    properties
    x
    y
    p
    end
    
    methods
        function g = log_plot_X(obj)
            figure
            g = semilogx(obj.x, obj.y);
        end
        
        function g = log_plot_Y(obj)
            figure
            g = semilogy(obj.x, obj.y);
        end
        
        function g = scater_plot(obj)
            g = scatter(obj.x, obj.y);
        end
        
        function g = f_plot(obj, from, to)
            g = fplot(obj.p, [from, to], 'LineWidth',2); 
        end
    end
end

