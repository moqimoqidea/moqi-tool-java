package com.moqi.in20210711;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * Tired of Null Pointer Exceptions? Consider Using Java SE 8's "Optional"!
 * https://www.oracle.com/technical-resources/articles/java/java8-optional.html
 *
 * @author moqi
 * On 2021/7/11 09:10
 */
@SuppressWarnings({
        "ConstantConditions",
        "AlibabaConstantFieldShouldBeUpperCase",
        "OptionalUsedAsFieldOrParameterType"
})
@Slf4j
public class DiveIntoJava8Optional {

    private static final Computer computer = new Computer();
    private static final Optional<ComputerOp> computerOp = Optional.ofNullable(new ComputerOp());

    private void m1() {
        String version = computer.getSoundcard().getUsb().getVersion();
    }

    private void m2() {
        String version = "UNKNOWN";
        if (computer != null) {
            Computer.Soundcard soundcard = computer.getSoundcard();
            if (soundcard != null) {
                Computer.Soundcard.USB usb = soundcard.getUsb();
                if (usb != null) {
                    version = usb.getVersion();
                }
            }
        }
    }

    private void m3() {
        String version = computerOp.flatMap(ComputerOp::getSoundcard)
                .flatMap(ComputerOp.SoundcardOp::getUsb)
                .map(ComputerOp.SoundcardOp.USBOp::getVersion)
                .orElse("UNKNOWN");
    }

    private void m4() {
        ComputerOp computerOp = DiveIntoJava8Optional.computerOp.orElse(new ComputerOp());
    }

    private void m5() {
        ComputerOp computerOp = DiveIntoJava8Optional.computerOp.orElseThrow(IllegalStateException::new);
    }

    private void m6() {
        ComputerOp computerOp = DiveIntoJava8Optional.computerOp.orElseGet(ComputerOp::new);
    }

    private void m7() {
        Optional<ComputerOp.SoundcardOp.USBOp> usbOp = computerOp.flatMap(ComputerOp::getSoundcard)
                .flatMap(ComputerOp.SoundcardOp::getUsb);
        ComputerOp.SoundcardOp.USBOp usbOp1 = usbOp.filter(usb -> "3.0".equals(usb.getVersion()))
                .orElse(null);
    }

}

@Data
class Computer {
    private Soundcard soundcard;

    @Data
    static class Soundcard {
        private USB usb;

        @Data
        static class USB {
            private String version;
        }

    }

}

/**
 * "Optional" should not be used for parameters
 * https://rules.sonarsource.com/java/RSPEC-3553
 *
 * Why should Java 8's Optional not be used in arguments
 * https://stackoverflow.com/a/31923105
 */
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
@Data
class ComputerOp {
    private Optional<SoundcardOp> soundcard;

    @Data
    static class SoundcardOp {
        private Optional<USBOp> usb;

        @Data
        static class USBOp {
            private String version;
        }

    }

}
