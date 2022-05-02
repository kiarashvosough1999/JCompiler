package Runner;

import gen.JythonLexer;
import gen.JythonListener;
import gen.JythonParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        CharStream stream = CharStreams.fromFileName("./Samples/test1.cl");
        JythonLexer lexer = new JythonLexer(stream);
        TokenStream tokenStream = new CommonTokenStream(lexer);
        JythonParser parser = new JythonParser(tokenStream);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.program();
        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        JythonListener listener = new ProgramPrinter();

        parseTreeWalker.walk(listener, tree);
    }
}
