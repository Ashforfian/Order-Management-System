package core;

import javax.swing.*;

public class Helper {
    public static void setTheme(){
        optionPaneDialogTR();
        for (UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()){
            if (info.getName().equals("Ninbus")){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
    }

    public static boolean isFieldListEmpty(JTextField[] fields){
        for (JTextField field : fields){
            if (field.getText().trim().isEmpty()){
                return true;
            }
        }
        return false;
    }

    public static boolean isMailValid(String mail){

        if (mail == null || mail.isEmpty()) return false;

        if (!mail.contains("@")) return false;

        String[] divided = mail.split("@");

        if (divided.length != 2) return false;

        if (divided[0].isEmpty() || divided[1].isEmpty()) return false;

        if (!divided[1].contains(".")) return false;

        return true;
    }
    public static void optionPaneDialogTR(){
        UIManager.put("OptionPane.okButtonText", "Tamam");
        UIManager.put("OptionPane.yesButtonText", "Evet");
        UIManager.put("OptionPane.noButtonText", "Hayır");
    }

    public static void showMessage(String message){
        String msg;
        String title;

        switch (message){
            case "fill":
                msg = "Lütfen tüm alanları doldurunuz!!";
                title = "Hata!";
                break;
            case "done":
                msg = "İşlem başarılı!";
                title = "Sonuç";
                break;
            case "error":
                msg = "Bir hata oluştu!";
                title = "Hata!";
                break;
            default:
                msg = message;
                title = "Mesaj";
        }

        JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(String str){
        optionPaneDialogTR();
        String message;
        if (str.equals("sure")){
            message = "Bu işlemi gerçekleştirmek istediğinize emin misiniz?";
        }else {
            message = str;
        }

        return JOptionPane.showConfirmDialog(null, message, "Emin miisin?",JOptionPane.YES_NO_OPTION) == 0;
    }
}
