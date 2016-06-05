classdef GraphLogScale
    properties
        x
        y
        p

        y1
        p1

        y2
        p2

    end
    
    methods
        function g = log_plot_X(obj, labelName, xlabelT, ylabelT)
            figure
            g = semilogx(obj.x, obj.y, 'LineWidth',2);
            xlabel(xlabelT);
            ylabel(ylabelT);
            legend(labelName, 'Location','southoutside');
        end
        
        function log_plot_Y(obj, labelName, xlabelT, ylabelT)
            figure
            semilogy(obj.x, obj.y, obj.x, obj.y1, obj.x, obj.y2, 'LineWidth',2);
            xlabel(xlabelT);
            ylabel(ylabelT);
            legend(labelName, 'Cholesky (Java)', 'LU (Java)', 'Location','southoutside');
        end


        function g = scater_plot(obj)
            g = scatter(obj.x, obj.y);
        end
        
        function g = f_plot(obj, from, to)
            g = fplot(obj.p, [from, to], 'LineWidth',2);
        end
    end
end

