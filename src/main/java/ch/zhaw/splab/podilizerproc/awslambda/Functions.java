package ch.zhaw.splab.podilizerproc.awslambda;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Functions {
    List<LambdaFunction> functions;

    public Functions(List<LambdaFunction> functions) {
        this.functions = functions;
    }

    public void write(){
        for (LambdaFunction function :
                functions) {
            try {
                String path = function.getAwsFiler().getPath().toString();
                function.getAwsFiler().createDirectories();
                File file  = new File(path + "/aws.java");
                PrintWriter printWriter = new PrintWriter(file);
                printWriter.print(function.create());
                printWriter.close();

                File input = new File(path + "/InputType.java");
                PrintWriter printWriter1 = new PrintWriter(input);
                printWriter1.print(function.createInputType());
                printWriter1.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
