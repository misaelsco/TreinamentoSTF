package br.com.treinamento.tr.teste.starter.utils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;

import cucumber.runtime.CucumberException;
import cucumber.runtime.io.URLOutputStream;
import gherkin.formatter.Formatter;
import gherkin.formatter.Reporter;
import gherkin.formatter.model.Background;
import gherkin.formatter.model.Examples;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Match;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.ScenarioOutline;
import gherkin.formatter.model.Step;
import gherkin.formatter.model.Tag;

public class ExtentCucumberFormatter implements Reporter, Formatter {

    public ExtentCucumberFormatter() {
        super();

        ExtentCucumberFormatter.initiateExtentCucumberFormatter();
        ExtentCucumberFormatter.loadConfig(new File("src/test/resources/extent-config.xml"));

        ExtentCucumberFormatter.addSystemInfo("Browser Name", "Firefox");
        ExtentCucumberFormatter.addSystemInfo("Browser version", "v31.0");
        ExtentCucumberFormatter.addSystemInfo("Selenium version", "v2.53.0");

        Map systemInfo = new HashMap();
        systemInfo.put("Cucumber version", "v1.2.3");
        systemInfo.put("Extent Cucumber Reporter version", "v1.1.0");
        ExtentCucumberFormatter.addSystemInfo(systemInfo);
    }

    private static ExtentReports extent;
    private ExtentTest featureTest;
    private ExtentTest scenarioTest;
    private static String screenshotPath = null;
    private static List<String> listaScreenShotPath = new ArrayList();
    private LinkedList<Step> testSteps = new LinkedList<Step>();
    private static File htmlReportDir;
    private static String htmlReportFileName = "report.html";
    private static Map systemInfo;
    private boolean scenarioOutlineTest;
    private static String detailsMessage;

    private static final Map<String, String> MIME_TYPES_EXTENSIONS = new HashMap() {

        {
            this.put("image/bmp", "bmp");
            this.put("image/gif", "gif");
            this.put("image/jpeg", "jpg");
            this.put("image/png", "png");
            this.put("image/svg+xml", "svg");
            this.put("video/ogg", "ogg");
        }
    };

    private static boolean isNumber(String value) {
        String pattern = "\\d+";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(value);

        return m.find();
    }

    private static boolean shouldUseMongoDb(String host, String port) {
        return host != null && isNumber(port);
    }

    public static String getReportPath() {
        return htmlReportDir.getPath();
    }

    public ExtentCucumberFormatter(File filePath) {
        if (!filePath.getPath().equals("")) {
            String reportPath = filePath.getPath();
            this.htmlReportDir = new File(reportPath);
            this.extent = new ExtentReports(reportPath);
        } else {
            String reportDir = "output/Run_" + System.currentTimeMillis();
            this.htmlReportDir = new File(reportDir);
            this.extent = new ExtentReports(reportDir + File.separator + htmlReportFileName);
        }

        String mongoDbHost = System.getenv("MONGODB_HOST");
        String mongoDbPort = System.getenv("MONGODB_PORT");
        String buildNumber = System.getenv("BUILD_REPOSITORY_NAME");

        if (shouldUseMongoDb(mongoDbHost, mongoDbPort)) {
            this.extent.x(mongoDbHost, Integer.parseInt(mongoDbPort));
        }

        if (buildNumber != null) {
            this.extent.assignProject(buildNumber);
        }
    }

    public static void initiateExtentCucumberFormatter(File filePath, Boolean replaceExisting,
            DisplayOrder displayOrder, NetworkMode networkMode,
            Locale locale) {
        htmlReportDir = filePath;
        System.setProperty("PATH_OUTPUT", filePath.getParent());
        extent = new ExtentReports(filePath.getAbsolutePath(), replaceExisting, displayOrder, networkMode, locale);

        String mongoDbHost = System.getenv("MONGODB_HOST");
        String mongoDbPort = System.getenv("MONGODB_PORT");
        String buildNumber = System.getenv("BUILD_REPOSITORY_NAME");
        String definitionName = System.getenv("Release.DefinitionName");

        if (shouldUseMongoDb(mongoDbHost, mongoDbPort)) {
            extent.x(mongoDbHost, Integer.parseInt(mongoDbPort));
        }

        if (buildNumber != null) {
            extent.assignProject(buildNumber);
        } else if (definitionName != null) {
            extent.assignProject(definitionName);
        }
    }

    public static void initiateExtentCucumberFormatter(File filePath, Boolean replaceExisting,
            DisplayOrder displayOrder, NetworkMode networkMode) {
        initiateExtentCucumberFormatter(filePath, replaceExisting, displayOrder, networkMode, null);
    }

    public static void initiateExtentCucumberFormatter(File filePath, Boolean replaceExisting,
            DisplayOrder displayOrder, Locale locale) {
        initiateExtentCucumberFormatter(filePath, replaceExisting, displayOrder, null, locale);
    }

    public static void initiateExtentCucumberFormatter(File filePath, Boolean replaceExisting,
            DisplayOrder displayOrder) {
        initiateExtentCucumberFormatter(filePath, replaceExisting, displayOrder, null, null);
    }

    public static void initiateExtentCucumberFormatter(File filePath, Boolean replaceExisting, NetworkMode networkMode,
            Locale locale) {
        initiateExtentCucumberFormatter(filePath, replaceExisting, null, networkMode, locale);
    }

    public static void initiateExtentCucumberFormatter(File filePath, Boolean replaceExisting,
            NetworkMode networkMode) {
        initiateExtentCucumberFormatter(filePath, replaceExisting, null, networkMode, null);
    }

    public static void initiateExtentCucumberFormatter(File filePath, NetworkMode networkMode) {
        initiateExtentCucumberFormatter(filePath, null, null, networkMode, null);
    }

    public static void initiateExtentCucumberFormatter(File filePath, Boolean replaceExisting, Locale locale) {
        initiateExtentCucumberFormatter(filePath, replaceExisting, null, null, locale);
    }

    public static void initiateExtentCucumberFormatter(File filePath, Boolean replaceExisting) {
        initiateExtentCucumberFormatter(filePath, replaceExisting, null, null, null);
    }

    public static void initiateExtentCucumberFormatter(File filePath, Locale locale) {
        initiateExtentCucumberFormatter(filePath, null, null, null, locale);
    }

    public static void initiateExtentCucumberFormatter(File filePath) {
        initiateExtentCucumberFormatter(filePath, null, null, null, null);
    }

    public static void initiateExtentCucumberFormatter() {
        String reportFilePath = "output" + File.separator + "Run_" + System.currentTimeMillis() + File.separator +
                htmlReportFileName;
        initiateExtentCucumberFormatter(new File(reportFilePath));
    }

    public static void setTestRunnerOutput(String s) {
        extent.setTestRunnerOutput(s);
    }

    public static void loadConfig(File configFile) {
        extent.loadConfig(configFile);
    }

    public static void addSystemInfo(String param, String value) {
        if (systemInfo == null) {
            systemInfo = new HashMap();
        }
        systemInfo.put(param, value);
    }

    public static void addSystemInfo(Map<String, String> info) {
        if (systemInfo == null) {
            systemInfo = new HashMap();
        }
        systemInfo.putAll(info);
    }

    public void before(Match match, Result result) {

    }

    public void result(Result result) {
        if (!scenarioOutlineTest) {
            Step step = testSteps.poll();
            
            // Caso o Step seja 'Sucesso' e não tenha recebido nenhum outro parametro (screenshots)
            if ("passed".equals(result.getStatus()) && screenshotPath == null && listaScreenShotPath.isEmpty()) {
                scenarioTest.log(LogStatus.PASS, step.getKeyword() + step.getName(), getStepDetailsMessage());
              
              // Caso o Step seja 'Sucesso' e tenha recebido uma única screenShot
            } else if ("passed".equals(result.getStatus()) && screenshotPath != null && listaScreenShotPath.isEmpty()) {
                scenarioTest.log(LogStatus.PASS, step.getKeyword() + step.getName(),
                        getStepDetailsMessage() + scenarioTest.addScreenCapture(screenshotPath));
                screenshotPath = null;
              
              // Caso o Step seja 'Sucesso' e tenha recebido uma lista de ScreenShots
            } else if ("passed".equals(result.getStatus()) && screenshotPath == null && !listaScreenShotPath.isEmpty()) {
                StringBuilder builder = new StringBuilder();
                
                // Adiciona todas os Paths de screenshots recebidos para um builder para que depois seja adicionado na descricao do Step
                for (String screenshot : listaScreenShotPath) {
                    builder.append(scenarioTest.addScreenCapture(screenshot));
                }
                
                scenarioTest.log(LogStatus.PASS, step.getKeyword() + step.getName(), getStepDetailsMessage() + builder.toString());
                listaScreenShotPath.clear();
            } else if ("failed".equals(result.getStatus())) {
                scenarioTest.log(LogStatus.FAIL, step.getKeyword() + step.getName(), result.getError());
            } else if ("skipped".equals(result.getStatus())) {
                scenarioTest.log(LogStatus.SKIP, step.getKeyword() + step.getName(), "SKIPPED");
            } else if ("undefined".equals(result.getStatus())) {
                scenarioTest.log(LogStatus.UNKNOWN, step.getKeyword() + step.getName(), "UNDEFINED");
            }
            setStepDetailsMessage("");
        }
    }

    public void after(Match match, Result result) {

    }

    public void match(Match match) {

    }

    public void embedding(String s, byte[] bytes) {
        if (!scenarioOutlineTest) {
            String extension = (String) MIME_TYPES_EXTENSIONS.get(s);
            String fileName = "screenshot-" + System.currentTimeMillis() + "." + extension;
            this.writeBytesAndClose(bytes, this.reportFileOutputStream(fileName));
            scenarioTest.log(LogStatus.INFO, scenarioTest.addScreenCapture(fileName));
        }
    }

    public void write(String s) {
        if (!scenarioOutlineTest)
            scenarioTest.log(LogStatus.INFO, s);
    }

    public void syntaxError(String s, String s1, List<String> list, String s2, Integer integer) {
    }

    public void uri(String s) {
    }

    public void feature(Feature feature) {
        featureTest = extent.startTest("Feature: " + feature.getName());

        for (Tag tag : feature.getTags()) {
            featureTest.assignCategory(tag.getName());
        }
    }

    public void scenarioOutline(ScenarioOutline scenarioOutline) {
        scenarioOutlineTest = true;
    }

    public void examples(Examples examples) {
    }

    public void startOfScenarioLifeCycle(Scenario scenario) {
        scenarioTest = extent.startTest("Scenario: " + scenario.getName());

        for (Tag tag : scenario.getTags()) {
            scenarioTest.assignCategory(tag.getName());
        }
        scenarioOutlineTest = false;
    }

    public void background(Background background) {
    }

    public void scenario(Scenario scenario) {
        System.out.println(scenario.toString());
    }

    public void step(Step step) {
        if (!scenarioOutlineTest)
            testSteps.add(step);
    }

    public void endOfScenarioLifeCycle(Scenario scenario) {
        if (!scenarioOutlineTest) {
            extent.endTest(scenarioTest);
            featureTest.appendChild(scenarioTest);
        }
    }

    public void done() {
    }

    public void close() {
        extent.addSystemInfo(systemInfo);
        extent.close();
    }

    public void eof() {
        extent.endTest(featureTest);
        extent.flush();
    }

    private OutputStream reportFileOutputStream(String fileName) {
        try {
            return new URLOutputStream(new URL(this.htmlReportDir.toURI().toURL(), fileName));
        } catch (IOException var3) {
            throw new CucumberException(var3);
        }
    }

    private void writeBytesAndClose(byte[] buf, OutputStream out) {
        try {
            out.write(buf);
        } catch (IOException var4) {
            throw new CucumberException("Unable to write to report file item: ", var4);
        }
    }

    public static String getStepDetailsMessage() {
        return detailsMessage;
    }

    /**
     * Irá adicionar uma única String na descrição do Step no relatório
     * @param detailsMessage
     */
    public static void setStepDetailsMessage(String detailsMessage) {
        ExtentCucumberFormatter.detailsMessage = detailsMessage;
    }

    /**
     * Irá adicionar uma String com a descrição do Step e uma Imagem (screenShot) na descrição do Step do relatório
     * @param detailsMessage
     * @param screenshotPathParam
     */
    public static void setStepDetailsMessage(String detailsMessage, String screenshotPathParam) {
        ExtentCucumberFormatter.detailsMessage = detailsMessage;
        screenshotPath = screenshotPathParam;
    }

    /**
     * Irá adicionar uma String com a descrição do Step e uma ou mais Imagens (screeShots) na descrição do Step do relatório
     * @param detailsMessage
     * @param listaScreenshotPathParam
     */
    public static void setStepDetailsMessage(String detailsMessage, List<String> listaScreenshotPathParam) {
        ExtentCucumberFormatter.detailsMessage = detailsMessage;

        for (String screenshot : listaScreenshotPathParam) {
            listaScreenShotPath.add(screenshot);
        }
    }

    public static void setReportFileName(String htmlReportFileName) {
        ExtentCucumberFormatter.htmlReportFileName = htmlReportFileName;
    }

}
