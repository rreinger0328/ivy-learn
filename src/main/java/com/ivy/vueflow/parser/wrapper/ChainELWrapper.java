package com.ivy.vueflow.parser.wrapper;

import com.yomahub.liteflow.builder.el.ELWrapper;

public class ChainELWrapper extends ELWrapper {

    private String chainId;

    public ChainELWrapper(String chainId) {
        this.chainId = chainId;
        this.setChainWrapper(this);
    }

    private void setChainWrapper(ELWrapper elWrapper) {
        this.addWrapper(elWrapper, 0);
    }

    private ChainELWrapper getChainWrapper() {
        return (ChainELWrapper)this.getFirstWrapper();
    }

    protected String getChainId() {
        return this.chainId;
    }

    protected void setChainId(String chainId) {
        this.chainId = chainId;
    }

    protected String toEL(Integer depth, StringBuilder paramContext) {
        ChainELWrapper chainELWrapper = this.getChainWrapper();
        StringBuilder sb = new StringBuilder();
        this.processWrapperTabs(sb, depth);
        sb.append(chainELWrapper.getChainId());
        this.processWrapperProperty(sb, paramContext);
        return sb.toString();
    }

}
