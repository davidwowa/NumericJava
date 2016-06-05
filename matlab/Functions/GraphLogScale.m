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
        function log_plot_X(obj, labelName, xlabelT, ylabelT)
            figure
            semilogx(obj.x, obj.y, 'LineWidth',2);
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

        function scater_plot(obj, labelName, xlabelT, ylabelT)
            figure
            scatter(obj.x, obj.y);
            xlabel(xlabelT);
            ylabel(ylabelT);
            legend(labelName, 'Location','southoutside');

        end
        
        function f_plot(obj)
            plot(obj.x, obj.y, 'LineWidth',2);
        end
    end
end

