import com.beust.jcommander.Parameter;
import lombok.Data;
import validators.EOLValidator;

import java.util.List;

@Data
class Args {

    @Parameter(names = {"--single"}, description = "Replace eol for single file. If none provided, all extensions will be changed.")
    private boolean singleFile;

    @Parameter(names = {"--path"}, description = "Path to file or directory. Default is current folder.", required = true)
    private String path;

    @Parameter(names = {"--extension"}, description = "File extensions that will be checked. May provide many.")
    private List<String> extensions;

    @Parameter(names = {"--symbol"}, description = "Symbol to be encoded. One of [CR, LF, CRLF].", validateWith = EOLValidator.class, required = true)
    private String symbol;

}
