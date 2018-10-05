package com.yufei.languagebasic.other;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

import static com.yufei.languagebasic.other.EnumStudy.CodeLanguage.PYTHON;
import static com.yufei.languagebasic.other.EnumStudy.CodeLanguage.SHELL;
import static com.yufei.languagebasic.other.EnumStudy.DemandStatus.UNTREATED;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnumStudy {
    private Long demandId;
    private String demandName;
    private String source;
    private Integer dataType;
    private Integer processSpeed;
    private String serviceName;
    private String serviceVersion;
    private String serviceTag;
    private Integer serviceType;
    private Boolean isRetry;
    private Integer retryPolicy;
    private String dataUrl;
    private Integer status;
    private String codeUrl;
    private Integer codeLanguage;
    private Boolean isPersistResult;
    private Integer command;
    private Date creationTime;
    private Date updateTime;

    public enum DemandStatus {UNTREATED, PRE_PROCESS, IN_PROCESS, FINISHED, PRE_PROCESS_FAIL, RETRYING, STOPPED, ERROR_OCCUR}

    public enum DataType {LONG_VIDEO, SHORT_VIDEO, PICTURE, TEXT}

    public enum RetryPolicy {EVERYDAY, ALL, IMMEDIATELY, NO}

    public enum CodeLanguage {SHELL, PYTHON, JAVA}

    public enum ServiceType {SYNCHRONIZE, ASYNCHRONIZE, WORK_FLOW}

    public enum Command {START, STOP, DESTORY}

    public enum CodeLangudage {
        SHELL(10, "a"), PYTHON(20, "20"), JAVA(30, "d");
        private Integer aa;
        private String ll;

        CodeLangudage(Integer aa, String ll) {
            this.aa = aa;
            this.ll = ll;
        }

        @Override
        public String toString() {
            return "CodeLangudage{" +
                    "aa=" + aa +
                    ", ll='" + ll + '\'' +
                    '}';
        }
    }

    public static DemandStatus toStatus(int status) {
        DemandStatus[] values = DemandStatus.values();
        for (int i = 0; i < values.length; i++) {
            if (status == i) {
                return values[i];
            }
        }
        return values[0];
    }

    public static void main(String[] args) {
        EnumStudy build = EnumStudy.builder().status(UNTREATED.ordinal()).build();
        Class<DemandStatus> declaringClass = UNTREATED.getDeclaringClass();
        String name = SHELL.name();
        System.out.println(name);
        System.out.println(SHELL);
        System.out.println(SHELL.toString());
        CodeLangudage shell = CodeLangudage.valueOf("SHELL");
        System.out.println(shell);
        System.out.println(SHELL.compareTo(PYTHON));
        System.out.println(SHELL.compareTo(SHELL));
        System.out.println(PYTHON.compareTo(SHELL));
    }
}




