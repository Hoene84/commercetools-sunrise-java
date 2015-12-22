package purchase;

import common.contexts.UserContext;
import common.controllers.ReverseRouter;
import common.models.ProductDataConfig;
import common.models.SelectableData;
import io.sphere.sdk.carts.Cart;
import play.i18n.Messages;

public class CheckoutConfirmationPageContent extends CheckoutPageContent {

    private CheckoutConfirmationFormBean checkoutForm;

    public CheckoutConfirmationPageContent() {
    }

    public CheckoutConfirmationPageContent(final Cart cart, final ProductDataConfig productDataConfig,
                                           final UserContext userContext, final ReverseRouter reverseRouter, final Messages messages) {
        setCart(new CartOrderBean(cart, productDataConfig, userContext, reverseRouter));
        final StepWidgetBean stepWidget = new StepWidgetBean();
        stepWidget.setConfirmationStepActive(true);
        setStepWidget(stepWidget);
        fillForm();
        setAdditionalTitle(messages.at("checkoutConfirmationPageTitle"));
    }

    private void fillForm() {
        final CheckoutConfirmationFormBean checkoutConfirmationFormBean = new CheckoutConfirmationFormBean();
        final SelectableData newsletter = new SelectableData();
        newsletter.setLabel("SUNRISE Newsletter");
        checkoutConfirmationFormBean.setNewsletter(newsletter);
        final SelectableData termsConditions = new SelectableData();
        checkoutConfirmationFormBean.setTermsConditions(termsConditions);
        setCheckoutForm(checkoutConfirmationFormBean);
    }

    public CheckoutConfirmationFormBean getCheckoutForm() {
        return checkoutForm;
    }

    public void setCheckoutForm(final CheckoutConfirmationFormBean checkoutForm) {
        this.checkoutForm = checkoutForm;
    }
}