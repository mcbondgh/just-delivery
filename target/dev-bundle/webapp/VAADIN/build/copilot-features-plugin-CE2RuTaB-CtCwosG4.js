import { y as yr, K as Ki, c as as } from "./indexhtml-H_vOFJvg.js";
import { g } from "./state-gDe32brS-BiCfH-Ut.js";
import { o } from "./base-panel-DRYiEh2Y-D1wtuBgb.js";
import { showNotification as N } from "./copilot-notification-CBGhtEfc-DzZBKf-6.js";
import { r as r$1 } from "./icons-B5-WIrwf-mZLce7vW.js";
const v = "copilot-features-panel{padding:var(--space-100);font:var(--font-xsmall);display:grid;grid-template-columns:auto 1fr;gap:var(--space-50);height:auto}copilot-features-panel a{display:flex;align-items:center;gap:var(--space-50);white-space:nowrap}copilot-features-panel a svg{height:12px;width:12px;min-height:12px;min-width:12px}";
var b = Object.defineProperty, F = Object.getOwnPropertyDescriptor, d = (e, t, a, o2) => {
  for (var r = o2 > 1 ? void 0 : o2 ? F(t, a) : t, s = e.length - 1, l; s >= 0; s--)
    (l = e[s]) && (r = (o2 ? l(t, a, r) : l(r)) || r);
  return o2 && r && b(t, a, r), r;
};
const n = window.Vaadin.devTools;
let i = class extends o {
  constructor() {
    super(...arguments), this.features = [], this.handleFeatureFlags = (e) => {
      this.features = e.data.features;
    };
  }
  connectedCallback() {
    super.connectedCallback(), this.onCommand("featureFlags", this.handleFeatureFlags);
  }
  render() {
    return yr` <style>
        ${v}
      </style>
      ${this.features.map(
      (e) => yr`
          <copilot-toggle-button
            .title="${e.title}"
            ?checked=${e.enabled}
            @on-change=${(t) => this.toggleFeatureFlag(t, e)}>
          </copilot-toggle-button>
          <a class="ahreflike" href="${e.moreInfoLink}" title="Learn more" target="_blank"
            >learn more ${r$1.linkExternal}</a
          >
        `
    )}`;
  }
  toggleFeatureFlag(e, t) {
    const a = e.target.checked;
    n.frontendConnection ? (n.frontendConnection.send("setFeature", { featureId: t.id, enabled: a }), N({
      type: Ki.INFORMATION,
      message: `“${t.title}” ${a ? "enabled" : "disabled"}`,
      details: t.requiresServerRestart ? "This feature requires a server restart" : void 0,
      dismissId: `feature${t.id}${a ? "Enabled" : "Disabled"}`
    })) : n.log("error", `Unable to toggle feature ${t.title}: No server connection available`);
  }
};
d([
  g()
], i.prototype, "features", 2);
i = d([
  as("copilot-features-panel")
], i);
const w = {
  header: "Features",
  expanded: true,
  draggable: true,
  panelOrder: 20,
  panel: "right",
  floating: false,
  tag: "copilot-features-panel",
  helpUrl: "https://vaadin.com/docs/latest/flow/configuration/feature-flags"
}, $ = {
  init(e) {
    e.addPanel(w);
  }
};
window.Vaadin.copilot.plugins.push($);
export {
  i as CopilotFeaturesPanel
};
